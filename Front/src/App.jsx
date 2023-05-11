import { Route, Routes, Navigate } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import { Footer } from "./components/footer";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <hr />
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/register" element={<Register/>}/>
            </Routes>
            <Footer/>
        </div>
        
    );
  }
  
  export default App;