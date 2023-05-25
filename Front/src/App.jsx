import { Route, Routes, Navigate } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import Login from "./routes/Login";
import CategoryPerros from "./routes/CategoryPerros";
import CategoryGatos from "./routes/CategoryGatos";
import CategoryCanarios from "./routes/CategoryCanarios";
import CategoryConejos from "./routes/CategoryConejos";
import ProductRegister from "./routes/ProductRegister";
import Details from "./routes/Details";
import { Footer } from "./components/footer";
import './styles.css';
import { CategoryFiltred } from "./routes/CategoryFiltred";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/Category/:id" element={<CategoryFiltred/>}/>
                <Route path="/Perros" element={<CategoryPerros/>}/>
                <Route path="/Gatos" element={<CategoryGatos/>}/>
                <Route path="/Canarios" element={<CategoryCanarios/>}/>
                <Route path="/Conejos" element={<CategoryConejos/>}/>
                <Route path="/Admin" element={<ProductRegister/>}/>
                <Route path="/Detail/:id" element={<Details/>}/>
            </Routes>
            <Footer/>
        </div>
        
    );
}

export default App;