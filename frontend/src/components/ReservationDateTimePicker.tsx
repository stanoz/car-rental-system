import { useEffect, useState } from "react";
import { times } from "../utils/times";
import useDispatchTyped from "../hooks/useDispatchTyped";
import { createReservationActions } from "../redux/create-reservation";
import useSelectorTyped from "../hooks/useSelectorTyped";
import { validateReservationDates } from "../utils/reservationValidation";

export default function ReservationDateTimePicker() {

    const { dates } = useSelectorTyped(state => state.createReservation);

    const [values, setValues] = useState({
        ...dates
    });

    const dispatch = useDispatchTyped();

    useEffect(() => {
        const { fromDate, fromTime, toDate, toTime } = values;
        if (!fromDate || !fromTime || !toDate || !toTime) {
            dispatch(createReservationActions.setStepNotCompleted());
        } else {
            dispatch(createReservationActions.completeStep());
        }

    }, []);

    const [error, setError] = useState({
        isError: false,
        message: ""
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setValues(prev => (
            {
                ...prev,
                [name]: value
            }
        ));
    }

    useEffect(() => {
        const { fromDate, fromTime, toDate, toTime } = values;

        if (!fromDate || !fromTime || !toDate || !toTime) {
            setError({
                isError: false,
                message: "",
            });
            return;
        }

        const start = new Date(`${fromDate}T${fromTime}`);
        const end = new Date(`${toDate}T${toTime}`);

        const validationResult = validateReservationDates(start, end);

        if (!validationResult.valid) {
            setError({
                isError: true,
                message: validationResult.message,
            });

            dispatch(createReservationActions.setStepNotCompleted());
            return;
        }

        setError({
            isError: false,
            message: "",
        });

        dispatch(createReservationActions.completeStep());
        dispatch(createReservationActions.setDates(values));

    }, [values]);

    return (
        <div className="flex flex-col gap-6 rounded-lg p-6 text-cyan-50 items-center">
            <h2 className="text-3xl">Select reservation date and time</h2>
            <div>
                <h2 className="mb-2 text-lg font-semibold">From</h2>
                <div className="flex gap-4">
                    <input
                        type="date"
                        name="fromDate"
                        value={values.fromDate}
                        onChange={handleChange}
                        className="rounded border px-3 py-2"
                    />
                    <select
                        name="fromTime"
                        value={values.fromTime}
                        onChange={handleChange}
                        className="rounded border px-3 py-2"
                    >
                        <option value="">Select time</option>

                        {times.map((time) => (
                            <option key={time} value={time}>
                                {time}
                            </option>
                        ))}
                    </select>
                </div>
            </div>
            <div>
                <h2 className="mb-2 text-lg font-semibold">To</h2>
                <div className="flex gap-4">
                    <input
                        type="date"
                        name="toDate"
                        value={values.toDate}
                        onChange={handleChange}
                        className="rounded border px-3 py-2"
                    />

                    <select
                        name="toTime"
                        value={values.toTime}
                        onChange={handleChange}
                        className="rounded border px-3 py-2"
                    >
                        <option value="">Select time</option>

                        {times.map((time) => (
                            <option key={time} value={time}>
                                {time}
                            </option>
                        ))}
                    </select>
                </div>
            </div>
            <div className="h-12">
                {error.isError && <p className="text-red-600 text-xl">{error.message}</p>}
            </div>
        </div>
    );
}