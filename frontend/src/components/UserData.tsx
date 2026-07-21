import { useState } from "react";
import useSelectorTyped from "../hooks/useSelectorTyped";
import Input from "./Input";

export default function UserData() {
    const { user } = useSelectorTyped(state => state.createReservation);

    const [values, setValues] = useState({
        ...user
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;

        setValues(prev => (
            {
                ...prev,
                [name]: value
            }
        ));
    }

    return (
        <div>
            <Input
                id="first-name"
                name="firstName"
                label="First name"
                value={values.firstName}
                onChange={handleChange}
                errorMessage="" />
            <Input
                id="last-name"
                name="lastName"
                label="Last name"
                value={values.lastName}
                onChange={handleChange}
                errorMessage="" />
            <Input
                id="email"
                name="emailAddress"
                label="Email address"
                value={values.emailAddress}
                onChange={handleChange}
                errorMessage="" />
            <Input
                id="phone"
                name="phoneNumber"
                label="Phone number"
                value={values.phoneNumber}
                onChange={handleChange}
                errorMessage="" />
            <Input
                id="driving-license"
                name="drivingLicenseId"
                label="Driving license ID"
                value={values.drivingLicenseId}
                onChange={handleChange}
                errorMessage="" />
        </div>
    );
}