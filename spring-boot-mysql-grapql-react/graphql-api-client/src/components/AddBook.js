import React, { useState, useRef } from "react";
import {
	getAuthorsQuery,
	addBookMutation,
	getBooksQuery
} from "../schema/queries";
import { useQuery, useMutation } from "@apollo/react-hooks";
import {genreOptions} from "../data/genre";

const AddBook = (props) => {
	const [name, setName] = useState("");
	const [genre, setGenre] = useState("");
    const [authorId, setAuthorId] = useState("");
    
    const refNameInput = useRef();
    const refGenreInput = useRef(); 
    const refSelInput = useRef();

	const { loading, error, data } = useQuery(getAuthorsQuery);
	//const [addBook, { data }] = useMutation(addBookMutation);
	const [addBook] = useMutation(addBookMutation);

	const displayAuthors = () => {
		if (loading) {
			return <option disabled>Loading Authors...</option>;
		} else {
			if (error) {
				props.errorHandler(error.message);
				return "";
			}
			return data.authors.map(author => {
				return (
					<option value={author.id} key={author.id}>
						{author.name}
					</option>
				);
			});
		}
	};

	const displayGenres = () => {
		return genreOptions.map(g => {
			return (
				<option value={g} key={g}>{g}</option>
			);
		})
	}

	const submitForm = e => {
		e.preventDefault();
		if(name !== "" && authorId !== ""){
			addBook({
				variables: {
					name,
					genre,
					authorId
				},
				refetchQueries: [{ query: getBooksQuery }]
			});
			props.errorHandler("");
			refNameInput.current.value = "";
			refGenreInput.current.value = "";
			refSelInput.current.value = "";
		}else{
			props.errorHandler("Error! Book name and the author are required");
		}
	};

	return (
		<form id="add-book" onSubmit={submitForm}>
			<div className="field">
				<label>*Book Name:</label>
				<input type="text" ref={refNameInput} onChange={e => setName(e.target.value)} />
			</div>

			<div className="field">
				<label>Genre:</label>
				<select onChange={e => setGenre(e.target.value)} ref={refGenreInput}>
					<option value="">Pick Genre</option>
					{displayGenres()}
				</select>
			</div>

			<div className="field">
				<label>*Author:</label>
				<select onChange={e => setAuthorId(e.target.value)} ref={refSelInput}>
					<option value="">Select Author</option>
					{displayAuthors()}
				</select>
			</div>

			<button>+</button>
		</form>
	);
};

export default AddBook;
