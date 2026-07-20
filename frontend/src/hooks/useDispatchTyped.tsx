import { useDispatch } from "react-redux";
import type { AppDispatch } from "../redux/store";

export default function useDispatchTyped() {
    return useDispatch<AppDispatch>();
}