package se.resonantri.stargazerutil.common;

import betterquesting.api.api.ApiReference;
import betterquesting.api.api.QuestingAPI;
import betterquesting.api.questing.tasks.ITaskRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.resonantri.stargazerutil.client.GuiProxy;
import se.resonantri.stargazerutil.common.blocks.BlockBookBindingTable;
import se.resonantri.stargazerutil.common.blocks.BlockScribeTable;
import se.resonantri.stargazerutil.common.blocks.ModBlocks;
import se.resonantri.stargazerutil.common.items.researchitems.inkwell.ItemInkwell;
import se.resonantri.stargazerutil.common.items.ItemParchment;
import se.resonantri.stargazerutil.common.items.ItemQuill;
import se.resonantri.stargazerutil.common.items.researchitems.ItemResearch;
import se.resonantri.stargazerutil.common.items.researchitems.ItemTheorem;
import se.resonantri.stargazerutil.common.items.researchitems.manuscripts.ItemManuscriptAboriculture;
import se.resonantri.stargazerutil.common.items.researchitems.manuscripts.ItemManuscriptAgriculture;
import se.resonantri.stargazerutil.common.items.researchitems.manuscripts.ItemManuscriptAtlas;
import se.resonantri.stargazerutil.common.items.researchitems.manuscripts.ItemManuscriptHusbandry;
import se.resonantri.stargazerutil.common.tiles.TileBookBindingTable;
import se.resonantri.stargazerutil.common.tiles.TileScribeTable;
import se.resonantri.stargazerutil.compat.CompatModule;
import se.resonantri.stargazerutil.compat.betterquesting.tasks.gamestages.getgamestage.TaskGetGameStageFactory;

import static se.resonantri.stargazerutil.StargazerUtil.instance;
import static se.resonantri.stargazerutil.StargazerUtil.logger;

@EventBusSubscriber
public class CommonProxy {

    public static Item INKWELL = new ItemInkwell();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        Block scribeTable = new BlockScribeTable();
        event.getRegistry().register(scribeTable);
        GameRegistry.registerTileEntity(TileScribeTable.class, scribeTable.getRegistryName().toString());

        Block bookBindingTable = new BlockBookBindingTable();
        event.getRegistry().register(bookBindingTable);
        GameRegistry.registerTileEntity(TileBookBindingTable.class, bookBindingTable.getRegistryName().toString());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemInkwell());
        event.getRegistry().register(new ItemQuill());
        event.getRegistry().register(new ItemParchment());
        event.getRegistry().register(new ItemResearch());
        event.getRegistry().register(new ItemTheorem());
        event.getRegistry().register(new ItemManuscriptAboriculture());
        event.getRegistry().register(new ItemManuscriptAgriculture());
        event.getRegistry().register(new ItemManuscriptAtlas());
        event.getRegistry().register(new ItemManuscriptHusbandry());
        event.getRegistry().register(new ItemBlock(ModBlocks.scribeTable).setRegistryName(ModBlocks.scribeTable.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.bookBindingTable).setRegistryName(ModBlocks.bookBindingTable.getRegistryName()));
    }

    public void registerExpansion(){
        ITaskRegistry taskRegistry = QuestingAPI.getAPI(ApiReference.TASK_REG);
        taskRegistry.registerTask(TaskGetGameStageFactory.INSTANCE);
    }

//    @SubscribeEvent
//    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
//        ItemStack stack = new ItemStack(INKWELL);
//        stack.setTagCompound(new NBTTagCompound());
//        stack.getTagCompound().setInteger("ink", 16);
//        event.getRegistry().register(new ShapelessOreRecipe(new ResourceLocation(Constants.MODID + ":inkwell"), stack, new ItemStack(INKWELL), new ItemStack(Items.DYE, 1, 0)).setRegistryName(new ResourceLocation(Constants.MODID + ":inkwellrefill")));
//    }

    public void preInit(FMLPreInitializationEvent e) {
        CompatModule.doModulesPreInit();
        logger.info("Pre-Initilization");
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
        CompatModule.doModulesInit();
        logger.info("Initilization");
    }

    public void postInit(FMLPostInitializationEvent e) {
        CompatModule.doModulesPostInit();
        logger.info("Post-Initilization");
    }

    public void serverStart(FMLServerStartingEvent e){
        CompatModule.doModulesLoadComplete();
        logger.info("Server Start");
    }
}
