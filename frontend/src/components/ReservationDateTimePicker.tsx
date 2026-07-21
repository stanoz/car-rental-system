import { useState } from "react";
import { times } from "../utils/times";

export default function ReservationDateTimePicker() {
    const [values, setValues] = useState({
        fromDate: "",
        fromTime: "",
        toDate: "",
        toTime: "",
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
                        onChange={handleChange}
                        className="rounded border px-3 py-2">
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
                        onChange={handleChange}
                        className="rounded border px-3 py-2">
                        {times.map((time) => (
                            <option key={time} value={time}>
                                {time}
                            </option>
                        ))}
                    </select>
                </div>
            </div>
        </div>
    );
}