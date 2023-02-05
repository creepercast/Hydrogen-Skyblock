package threshold.hydrogenation_tinker;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class BlockShinning extends Block {

    public BlockShinning() {
        super(Material.ROCK);
    }
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isCollidable()
    {
        return false;
    }

    // func_149686_d，返回 false 代表这个方块不是完整的 1x1x1 立方体。
    // 和 isOpaqueCube 不一样的地方在于，这个方法会影响窒息、碰撞等判定及光照计算。
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    // func_149662_c，返回 false 代表这个方块是完整的 1x1x1 的正方体，且没有透明材质
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    // func_149645_b，返回 EnumBlockRenderType.INVISIBLE 代表不渲染这个方块
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }
    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos) {
        return NULL_AABB;
    }
    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return new AxisAlignedBB(0.25, 0.25, 0.25, 0.75, 0.75, 0.75);
    }
    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return 4;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityShinning();
    }
}
