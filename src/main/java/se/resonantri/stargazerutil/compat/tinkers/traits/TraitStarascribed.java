package se.resonantri.stargazerutil.compat.tinkers.traits;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class TraitStarascribed extends AbstractTraitLeveled {

    public TraitStarascribed(int levels) {
        super("starascribed", String.valueOf(levels), 0x351C75, 2, 1);
    }

    @Override
    public void applyModifierEffect(NBTTagCompound rootCompound) {
        NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
        int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + levels;
        toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
        TagUtil.setToolTag(rootCompound, toolTag);
    }
}
