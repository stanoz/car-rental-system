import { createSlice } from "@reduxjs/toolkit"
import type { CarDto } from "../model/car"
import { CarCategory } from "../model/enum/car-category"
import type { PayloadAction } from "@reduxjs/toolkit"

type CarInitialState = {
    car: CarDto
}

const initialState: CarInitialState = {
    car: {
        id: "",
        brand: "",
        costPerDay: 0,
        inStock: 0,
        type: CarCategory.SEDAN
    }
}
export const carSlice = createSlice({
    name: "car",
    initialState,
    reducers: {
        setCar(state, action: PayloadAction<CarDto>) {
            state.car = action.payload
        }
    }
})

export const carActions = carSlice.actions;
