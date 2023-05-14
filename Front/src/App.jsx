import { Route, Routes, Navigate } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import Login from "./routes/Login";
import CategoryFinca from "./routes/CategoryFinca";
import CategoryCasa from "./routes/CategoryCasa";
import CategoryGuarderia from "./routes/CategoryGuarderia";
import { Footer } from "./components/footer";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <div class="container">
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/finca" element={<CategoryFinca/>}/>
                <Route path="/casa" element={<CategoryCasa/>}/>
                <Route path="/guarderia" element={<CategoryGuarderia/>}/>
            </Routes>
            </div>
            <Footer/>
        </div>
        
    );
}

export default App;