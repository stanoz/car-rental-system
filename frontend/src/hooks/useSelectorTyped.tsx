import { useSelector } from "react-redux";
import type { RootState } from "../redux/store";

export default function useSelectorTyped<T>(selector: (state: RootState) => T) {
    return useSelector(selector);
}