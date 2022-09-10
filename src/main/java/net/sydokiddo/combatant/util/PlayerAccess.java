package net.sydokiddo.combatant.util;

// Player Access Code for Sickles

public interface PlayerAccess {

    void resetLastOffhandAttackTicks();

    void setOffhandAttack();

    boolean isOffhandAttack();

    float getAttackCooldownProgressOffhand(float baseTime);
}