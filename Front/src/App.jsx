import { Route, Routes, Navigate } from "react-router-dom";
import { NavBar } from "./components/NavBar";
import { Home } from "./routes/Home";
import Register from "./routes/Register";
import Login from "./routes/Login";
import {CategoryFiltred} from "./routes/CategoryFiltred";
import ProductRegister from "./routes/ProductRegister";
import Details from "./routes/Details";
import MyAccount from "./routes/MyAccount";
import EditProducts from "./routes/EditProducts";
import { Footer } from "./components/footer";
import './styles.css';
import { FormNewCategory } from "./routes/FormNewCategory";
import { Mail } from "./routes/Mail";
import { MailVerification } from "./routes/MailVerification";

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
                <Route path="/newCategory" element={<FormNewCategory/>}/>
                <Route path="/account/:id" element={<MyAccount/>}/>
                <Route path="/editProducts" element={<EditProducts/>}/>
                <Route path="/mailConfirmation" element={<Mail/>}/>
                <Route path="/mailVerifirmation" element={<MailVerification/>}/>
            </Routes>
            <Footer/>
        </div>
        
    );
}

export default App;