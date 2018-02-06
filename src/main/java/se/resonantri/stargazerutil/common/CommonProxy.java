package se.resonantri.stargazerutil.common;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import se.resonantri.stargazerutil.client.GuiProxy;
import se.resonantri.stargazerutil.common.blocks.BlockBookBindingTable;
import se.resonantri.stargazerutil.common.blocks.BlockScribeTable;
import se.resonantri.stargazerutil.common.blocks.ModBlocks;
import se.resonantri.stargazerutil.common.items.ItemInkwell;
import se.resonantri.stargazerutil.common.items.ItemQuill;
import se.resonantri.stargazerutil.common.tiles.TileBookBindingTable;
import se.resonantri.stargazerutil.common.tiles.TileScribeTable;
import se.resonantri.stargazerutil.utils.Constants;

import static se.resonantri.stargazerutil.StargazerUtil.instance;

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
        event.getRegistry().register(new ItemBlock(ModBlocks.scribeTable).setRegistryName(ModBlocks.scribeTable.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.bookBindingTable).setRegistryName(ModBlocks.bookBindingTable.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
        ItemStack stack = new ItemStack(INKWELL);
        int currentInk = stack.getTagCompound().getInteger("ink");
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("ink", currentInk + 16);
        event.getRegistry().register(new ShapelessOreRecipe(new ResourceLocation(Constants.MODID + ":inkwell"), stack, new ItemStack(INKWELL), new ItemStack(Items.DYE, 1, 0)).setRegistryName(new ResourceLocation(Constants.MODID + ":inkwellrefill")));
    }


    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
