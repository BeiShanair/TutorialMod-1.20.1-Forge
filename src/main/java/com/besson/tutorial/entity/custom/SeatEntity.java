package com.besson.tutorial.entity.custom;

import com.besson.tutorial.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class SeatEntity extends Entity {
    public SeatEntity(Level pLevel) {
        super(ModEntities.SEAT.get(), pLevel);
        this.noPhysics = true;
    }
    
    private SeatEntity(Level level, BlockPos pos, double yOffset, Direction direction) {
        this(level);
        this.setPos(pos.getX() + 0.5, pos.getY() + yOffset, pos.getZ() + 0.5);
        this.setRot(direction.toYRot(), 0.0f);
    }

    @Override
    protected void defineSynchedData() {
        
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            if (this.getPassengers().isEmpty() || this.level().isEmptyBlock(this.blockPosition())) {
                this.remove(RemovalReason.DISCARDED);
                this.level().updateNeighbourForOutputSignal(blockPosition(), this.level().getBlockState(blockPosition()).getBlock());
            }
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.0;
    }

    @Override
    protected boolean canRide(Entity pVehicle) {
        return true;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pPassenger) {
        Direction original = this.getDirection();
        Direction[] offsets = {original, original.getClockWise(), original.getCounterClockWise(), original.getOpposite()};
        for (Direction direction : offsets) {
            Vec3 safeVec = DismountHelper.findSafeDismountLocation(pPassenger.getType(), this.level(), this.blockPosition().relative(direction), false);
            if (safeVec != null) {
                return safeVec.add(0, 0.25, 0);
            }
        }
        return super.getDismountLocationForPassenger(pPassenger);
    }
    
    public static InteractionResult create(Level level, BlockPos pos, double yOffset, Player player, Direction direction) {
        if (!level.isClientSide()) {
            List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1));
            if (seats.isEmpty()) {
                SeatEntity seat = new SeatEntity(level, pos, yOffset, direction);
                level.addFreshEntity(seat);
                player.startRiding(seat, false);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void addPassenger(Entity pPassenger) {
        super.addPassenger(pPassenger);
        pPassenger.setYRot(this.getYRot());
    }

    @Override
    protected void positionRider(Entity pPassenger, MoveFunction pCallback) {
        super.positionRider(pPassenger, pCallback);
        this.clampYaw(pPassenger);
    }

    private void clampYaw(Entity pPassenger) {
        pPassenger.setYBodyRot(this.getYRot());
        float wrappedYaw = Mth.wrapDegrees(pPassenger.getYRot() - this.getYRot());
        float clampedYaw = Mth.clamp(wrappedYaw, -120.0f, 120.0f);
        pPassenger.yRotO += clampedYaw - wrappedYaw;
        pPassenger.setYRot(pPassenger.getYRot() + clampedYaw - wrappedYaw);
        pPassenger.setYHeadRot(pPassenger.getYRot());
    }
}
