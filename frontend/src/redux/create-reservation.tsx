import { createSlice, type PayloadAction } from "@reduxjs/toolkit";

type CreateReservationDates = {
    fromDate: string,
    fromTime: string,
    toDate: string,
    toTime: string,
}

type CreateReservationUserData = {
    firstName: string,
    lastName: string,
    emailAddress: string,
    phoneNumber: string,
    drivingLicenseId: string,
}

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
        setUserData(state, action: PayloadAction<CreateReservationUserData>) {
            state.user = action.payload;
        }
    }
});

export const createReservationActions = createReservationSlice.actions;