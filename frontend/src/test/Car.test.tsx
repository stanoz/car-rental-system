import { describe, it, expect, vi, beforeEach } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import { carActions } from "../redux/car";
import Car from "../components/Car";
import type { CarDto } from "../model/car";
import { CarCategory } from "../model/enum/car-category";

const dispatch = vi.fn();
const navigate = vi.fn();

vi.mock("../hooks/useDispatchTyped", () => ({
    default: () => dispatch,
}));

vi.mock("react-router", () => ({
    useNavigate: () => navigate,
}));

const car: CarDto = {
    id: 1,
    brand: "Brand",
    type: CarCategory.SUV,
    costPerDay: 50,
    inStock: 3,
};

describe("Car", () => {

    beforeEach(() => {
        dispatch.mockClear();
        navigate.mockClear();
        render(<Car car={car} />);
    });

    it("renders car information", () => {

        expect(screen.getByText(/Car type: Suv/i)).toBeInTheDocument();
        expect(screen.getByText(/Brand: Brand/i)).toBeInTheDocument();
        expect(screen.getByText(/Cost per day: \$50\.00/i)).toBeInTheDocument();
        expect(screen.getByText(/Available: 3/i)).toBeInTheDocument();
    });

    it("dispatches selected car and navigates when clicked", async () => {
        const user = userEvent.setup();

        await user.click(screen.getByText(/Car type: Suv/i));

        expect(dispatch).toHaveBeenCalledWith(
            carActions.setCar(car)
        );

        expect(navigate).toHaveBeenCalledWith(
            "/create-reservation"
        );
    });
});