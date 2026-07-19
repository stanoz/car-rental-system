import { Outlet } from "react-router";
import Header from "./Header";

export default function Layout() {
    return (
        <div className="flex flex-col items-center bg-slate-800 w-screen h-screen">
            <Header />
            <main >
                <Outlet />
            </main>
        </div>
    );
}