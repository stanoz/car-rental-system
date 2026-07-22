import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import type { CreateReservationDates, CreateReservationUserData } from "../model/reservation";

type CreateReservationInitialState = {
    isStepCompleted: boolean,
    dates: CreateReservationDates,
    user: CreateReservationUserData,
}

const initialState: CreateReservationInitialState = {
    isStepCompleted: false,
    dates: {
        fromDate: "",
        fromTime: "",
        toDate: "",
        toTime: "",
    },
    user: {
        firstName: "",
        lastName: "",
        emailAddress: "",
        phoneNumber: "",
        drivingLicenseId: ""
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
        },
        setDatesToDefault(state) {
            state.dates = {
                fromDate: "",
                fromTime: "",
                toDate: "",
                toTime: "",
            }
        },
        setUserData(state, action: PayloadAction<CreateReservationUserData>) {
            state.user = action.payload;
        },
        setUserDataToDefault(state) {
            state.user = {
                firstName: "",
                lastName: "",
                emailAddress: "",
                phoneNumber: "",
                drivingLicenseId: ""
            }
        }
    }
});

export const createReservationActions = createReservationSlice.actions;