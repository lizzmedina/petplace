import { Route, Routes } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import Login from "./routes/Login";
import {CategoryFiltred} from "./routes/CategoryFiltred";
import ProductRegister from "./routes/ProductRegister";
import CityRegister from "./routes/CityRegister";
import Details from "./routes/Details";
import MyAccount from "./routes/MyAccount";
import EditProducts from "./routes/EditProducts";
import EditUsers from "./routes/EditUsers";
import EditCity from "./routes/EditCity";
import EditCategory from "./routes/EditCategory";
import { Footer } from "./components/Footer";
import './styles.css';
import { ValidationPage } from "./routes/ValidationPage";
import { NewCategory } from "./routes/NewCategory";
import ContextProvider from "../src/components/utils/global.constext";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <ContextProvider>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/category/:id" element={<CategoryFiltred/>}/>
                <Route path="/productRegister" element={<ProductRegister/>}/>
                <Route path="/cityRegister" element={<CityRegister/>}/>
                <Route path="/Detail/:id" element={<Details/>}/>
                <Route path="/newCategory" element={<NewCategory/>}/>
                <Route path="/account/:id" element={<MyAccount/>}/>
                <Route path="/editProducts" element={<EditProducts/>}/>
                <Route path="/editUsers" element={<EditUsers/>}/>
                <Route path="/editCity" element={<EditCity/>}/>
                <Route path="/editCategory" element={<EditCategory/>}/>
                <Route path="/validationPage/" element={<ValidationPage/>}/>
            </Routes>
            </ContextProvider>
            <Footer/>
        </div>
        
    );
}

export default App;