import { createSlice } from "@reduxjs/toolkit";

type CreateReservationInitialState = {
    isStepCompleted: boolean,
}

const initialState: CreateReservationInitialState = {
    isStepCompleted: false,
};

export const createReservationSlice = createSlice({
    name: "createReservation",
    initialState,
    reducers: {
        completeStep(state) {
            state.isStepCompleted = true;
        },
        setStepNotCompleted(state) {
            state.isStepCompleted = false;
        }
    }
});

export const createReservationActions = createReservationSlice.actions;