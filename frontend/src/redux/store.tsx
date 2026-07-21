import { configureStore } from "@reduxjs/toolkit";
import { carSlice } from "./car";
import { createReservationSlice } from "./create-reservation";

export const store = configureStore({
    reducer: {
        car: carSlice.reducer,
        createReservation: createReservationSlice.reducer
    }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
