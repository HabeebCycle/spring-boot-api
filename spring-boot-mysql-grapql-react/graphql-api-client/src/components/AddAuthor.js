import React, { useState, useRef } from "react";
import {
	addAuthorMutation,
	getAuthorsQuery
} from "../schema/queries";
import { useQuery, useMutation } from "@apollo/react-hooks";

const AddAuthor = () => {
    const [name, setName] = useState("");
    const [age, setAge] = useState("");
    
    return (
        <div>
            
        </div>
    )
}

export default AddAuthor
