import { createSlice, type PayloadAction } from "@reduxjs/toolkit";

type CreateReservationDates = {
    fromDate: string,
    fromTime: string,
    toDate: string,
    toTime: string,
}

type CreateReservationInitialState = {
    isStepCompleted: boolean,
    dates: CreateReservationDates
}

const initialState: CreateReservationInitialState = {
    isStepCompleted: false,
    dates: {
        fromDate: "",
        fromTime: "",
        toDate: "",
        toTime: "",
    }
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
        },
        setDates(state, action: PayloadAction<CreateReservationDates>) {
            state.dates = action.payload;
        }
    }
});

export const createReservationActions = createReservationSlice.actions;