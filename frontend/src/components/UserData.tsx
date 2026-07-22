import { useEffect, useState } from "react";
import useSelectorTyped from "../hooks/useSelectorTyped";
import Input from "./Input";
import { userDataSchema } from "../validation-schemas/userDataSchema";
import useDispatchTyped from "../hooks/useDispatchTyped";
import { createReservationActions } from "../redux/create-reservation";

const errorMessagesInitialState = {
    firstName: "",
    lastName: "",
    emailAddress: "",
    phoneNumber: "",
    drivingLicenseId: ""
};

export default function UserData() {
    const { user } = useSelectorTyped(state => state.createReservation);

    const dispatch = useDispatchTyped();

    const [values, setValues] = useState({
        ...user
    });
    const [errorMessages, setErrorMessages] = useState(errorMessagesInitialState);

    useEffect(() => {
        const { firstName, lastName, emailAddress, phoneNumber, drivingLicenseId } = user;
        if (!firstName || !lastName || !emailAddress || !phoneNumber || !drivingLicenseId) {
            dispatch(createReservationActions.setStepNotCompleted());
        } else {
            dispatch(createReservationActions.completeStep());
        }
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;

        validateField(name, value);

        setValues(prev => (
            {
                ...prev,
                [name]: value
            }
        ));
    }

    const validateField = (name: string, value: string) => {
        const validationResult = userDataSchema.shape[name as keyof typeof userDataSchema.shape].safeParse(value);
        if (validationResult.success) {
            setErrorMessages(prev => (
                {
                    ...prev,
                    [name]: ""
                }
            ));
        } else {
            setErrorMessages(prev => (
                {
                    ...prev,
                    [name]: validationResult.error.issues[0].message
                }
            ));
        }
    }

    useEffect(() => {
        const validationResult = userDataSchema.safeParse(values);
        if (validationResult.success) {
            setErrorMessages(errorMessagesInitialState);
            dispatch(createReservationActions.setUserData(values));
            dispatch(createReservationActions.completeStep());
        } else {
            dispatch(createReservationActions.setStepNotCompleted());
        }
    }, [values]);

    return (
        <div className="flex flex-col items-center mt-8">
            <h2 className="text-3xl text-cyan-50">Your data</h2>
            <div className="flex flex-col items-center mb-4 mt-8">
                <Input
                    id="first-name"
                    name="firstName"
                    label="First name"
                    value={values.firstName}
                    onChange={handleChange}
                    errorMessage={errorMessages.firstName} />
                <Input
                    id="last-name"
                    name="lastName"
                    label="Last name"
                    value={values.lastName}
                    onChange={handleChange}
                    errorMessage={errorMessages.lastName} />
                <Input
                    id="email"
                    name="emailAddress"
                    type="email"
                    label="Email address"
                    value={values.emailAddress}
                    onChange={handleChange}
                    errorMessage={errorMessages.emailAddress} />
                <Input
                    id="phone"
                    name="phoneNumber"
                    label="Phone number"
                    value={values.phoneNumber}
                    onChange={handleChange}
                    errorMessage={errorMessages.phoneNumber} />
                <Input
                    id="driving-license"
                    name="drivingLicenseId"
                    label="Driving license ID"
                    value={values.drivingLicenseId}
                    onChange={handleChange}
                    errorMessage={errorMessages.drivingLicenseId} />
            </div>
        </div>
    );
}