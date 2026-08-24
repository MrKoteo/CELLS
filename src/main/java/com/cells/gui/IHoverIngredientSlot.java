package com.cells.gui;

import javax.annotation.Nullable;


/**
 * Marker for slot-like hover surfaces that expose a non-vanilla ingredient.
 */
public interface IHoverIngredientSlot {

    @Nullable
    Object getIngredient();
}