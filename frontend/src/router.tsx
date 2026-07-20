import { createBrowserRouter } from "react-router";
import Layout from "./layout/Layout";
import CarList from "./components/CarList";
import CreateReservationFormContainer from "./components/CreateReservationFormContainer";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        children: [
            {
                index: true,
                element: <CarList />
            },
            {
                path: "create-reservation",
                element: <CreateReservationFormContainer />
            }
        ]
    }
]);