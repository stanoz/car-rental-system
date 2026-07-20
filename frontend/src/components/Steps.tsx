import Step from "./Step";
import { PiNumberCircleFour, PiNumberCircleOne, PiNumberCircleThree, PiNumberCircleTwo } from "react-icons/pi";
import { FaMinus } from "react-icons/fa";

type StepsProps = {
    stepNumber: number
}

const activeTextClass = "text-emerald-600";
const inActiveTextClass = "text-gray-400";

export default function Steps({ stepNumber }: StepsProps) {

    return (
        <div className="flex items-center mt-10">
            <Step isActive={stepNumber >= 1} icon={PiNumberCircleOne} />
            <FaMinus size={20} className={stepNumber >= 2 ? activeTextClass : inActiveTextClass}/>
            <Step isActive={stepNumber >= 2} icon={PiNumberCircleTwo} />
            <FaMinus size={20} className={stepNumber >= 3 ? activeTextClass : inActiveTextClass} />
            <Step isActive={stepNumber >= 3} icon={PiNumberCircleThree} />
            <FaMinus size={20} className={stepNumber >= 4 ? activeTextClass : inActiveTextClass}/>
            <Step isActive={stepNumber >= 4} icon={PiNumberCircleFour} />
        </div>
    );
}