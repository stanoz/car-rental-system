import { createSlice, type PayloadAction } from "@reduxjs/toolkit"

type ReservationConfirmationInitialState = {
    id: number,
}

const initialState: ReservationConfirmationInitialState = {
    id: 0,
}

export const reservationConfirmationSlice = createSlice({
    name: "reservationConfirmation",
    initialState,
    reducers: {
        setReservationId(state, action: PayloadAction<number>) {
            state.id = action.payload;
        }
    }
});

export const reservationConfirmationActions = reservationConfirmationSlice.actions;
