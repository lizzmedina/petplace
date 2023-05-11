import React, { useState } from "react";

const FormUser = () => {
    const [user, setUser] = useState({
        name:'',
        lastName:'',
        cellPhone:'',
        address:'',
        email:'',
        password:'',
        type:''
    })

    const handleSubmit = (event) => {
        event.preventDefault()
        if (user.name.length > 5) {
            alert(`Hi ${user.name}, your account has been successfully created.`)
            setUser({
                name:'',
                lastName:'',
                cellPhone:'',
                address:'',
                email:'',
                password:'',
                type:''
            })
        } else {
            alert('Please check the information provided')
        }
    }

    return (
        <div className="formuser-container">
            <form className="formuser-section" onSubmit={handleSubmit}>
                <label>Name: </label>
                <input type="text" value={user.name} onChange={(e) => setUser({...user, name: e.target.value})}/>
                <br/>
                <label>LastName: </label>
                <input type="text" value={user.lastName} onChange={(e) => setUser({...user, lastName: e.target.value})}/>
                <br/>
                <label>CellPhone: </label>
                <input type="text" value={user.cellPhone} onChange={(e) => setUser({...user, cellPhone: e.target.value})}/>
                <br/>
                <label>Address: </label>
                <input type="text" value={user.address} onChange={(e) => setUser({...user, address: e.target.value})}/>
                <br/>
                <label>Email: </label>
                <input type="email" value={user.email} onChange={(e) => setUser({...user, email: e.target.value})}/>
                <br/>
                <label>Password: </label>
                <input type="password" value={user.password} onChange={(e) => setUser({...user, password: e.target.value})}/>
                <br/>
                <label>User Type: </label>
                <select name="type" onChange={(e) => setUser({...user, type: e.target.value})}>
                    <option value="customer">Customer</option>
                    <option value="manager">Manager</option>
                </select>

                <button>Send</button>
            </form>
        </div>
    );
};

export default FormUser