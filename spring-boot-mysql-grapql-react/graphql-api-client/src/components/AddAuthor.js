import React, { useState, useRef } from "react";
import {
	addAuthorMutation,
	getAuthorsQuery
} from "../schema/queries";
import { useMutation } from "@apollo/react-hooks";

const AddAuthor = (props) => {
    const [name, setName] = useState("");
    const [age, setAge] = useState("");

    const refNameInput = useRef();
    const refAgeInput = useRef();

    const [addAuthor] = useMutation(addAuthorMutation);

    const submitForm = e => {
		e.preventDefault();
		if(name !== ""){
			addAuthor({
				variables: {
					name,
					age
				},
				refetchQueries: [{ query: getAuthorsQuery }]
			});
			props.errorHandler("");
			refNameInput.current.value = "";
			refAgeInput.current.value = "";
		}else{
			props.errorHandler("Error! Author name is required");
		}
	};
    
    return (
        <form id="add-author" onSubmit={submitForm}>
            <h3>Add New Author</h3>
			<div className="field">
				<label>*Author Name:</label>
                <input type="text" ref={refNameInput} onChange={e => setName(e.target.value)} />
                <span></span>
			</div>

			<div className="field">
				<label>Age:</label>
                <input type="text" ref={refAgeInput} onChange={e => setAge(e.target.value)} />
                <span></span>
			</div>

			<button>+</button>
		</form>
    )
}

export default AddAuthor
