import { configureStore } from "@reduxjs/toolkit";
import { carSlice } from "./car";
import { createReservationSlice } from "./create-reservation";
import { reservationConfirmationSlice } from "./reservation-confirmation";

export const store = configureStore({
    reducer: {
        car: carSlice.reducer,
        createReservation: createReservationSlice.reducer,
        reservationConfirmation: reservationConfirmationSlice.reducer,
    }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
