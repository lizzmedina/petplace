import { Route, Routes, Navigate } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import Login from "./routes/Login";
import CategoryFinca from "./routes/CategoryFinca";
import { Footer } from "./components/footer";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <hr />
            <div class="container">
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/finca" element={<CategoryFinca/>}/>
            </Routes>
            </div>
            <Footer/>
        </div>
        
    );
}

export default App;