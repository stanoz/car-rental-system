import Step from "./Step";
import { PiNumberCircleFour, PiNumberCircleOne, PiNumberCircleThree, PiNumberCircleTwo } from "react-icons/pi";
import { FaMinus } from "react-icons/fa";

type StepsProps = {
    stepNumber: number
}

export default function Steps({ stepNumber }: StepsProps) {

    return (
        <div>
            <Step isActive={stepNumber >= 1} icon={PiNumberCircleOne} />
            <FaMinus />
            <Step isActive={stepNumber >= 2} icon={PiNumberCircleTwo} />
            <FaMinus />
            <Step isActive={stepNumber >= 3} icon={PiNumberCircleThree} />
            <FaMinus />
            <Step isActive={stepNumber >= 4} icon={PiNumberCircleFour} />
        </div>
    );
}