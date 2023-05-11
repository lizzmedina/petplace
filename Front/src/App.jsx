import { Route, Routes, Navigate } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import { Footer } from "./components/footer";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <hr />
            <Routes>
                <Route path="/" element={<Home/>}/>
            </Routes>
            <Footer/>
        </div>
        
    );
  }
  
  export default App;