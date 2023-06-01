import { Route, Routes } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import Login from "./routes/Login";
import {CategoryFiltred} from "./routes/CategoryFiltred";
import ProductRegister from "./routes/ProductRegister";
import Details from "./routes/Details";
import MyAccount from "./routes/MyAccount";
import EditProducts from "./routes/EditProducts";
import EditUsers from "./routes/EditUsers";
import { Footer } from "./components/Footer";
import './styles.css';
import { FormNewCategory } from "./components/FormNewCategory";
import { ValidationPage } from "./routes/ValidationPage";
import { NewCategory } from "./routes/NewCategory";

function App() {
    return (
        <div className="App">
            <NavBar/>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/category/:id" element={<CategoryFiltred/>}/>
                <Route path="/productRegister" element={<ProductRegister/>}/>
                <Route path="/Detail/:id" element={<Details/>}/>
                <Route path="/newCategory" element={<NewCategory/>}/>
                <Route path="/account/:id" element={<MyAccount/>}/>
                <Route path="/editProducts" element={<EditProducts/>}/>
                <Route path="/editUsers" element={<EditUsers/>}/>
                <Route path="/validationPage" element={<ValidationPage/>}/>
            </Routes>
            <Footer/>
        </div>
        
    );
}

export default App;