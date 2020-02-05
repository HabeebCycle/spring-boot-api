import React from "react";
import { getBooksQuery } from "../schema/queries";
import { useQuery } from "@apollo/react-hooks";


const BookList = (props) => {
	const { loading, error, data } = useQuery(getBooksQuery);

	const displayBooks = () => {
		if (loading) {
			return <div>Loading Books...</div>;
		} else {
			if (error) {
				props.errorHandler(error.message);
				return <div>{error.message}</div>;
			}
			return data.books.map(book => {
				return (
					<li
						key={book.id}
						onClick={e => {
							props.detailsHandler("Book", book.id);
						}}
					>
						{book.name}
					</li>
				);
			});
		}
	};

	return (
		<div>
			<ul id="book-list">{displayBooks()}</ul>
		</div>
	);
};

export default BookList;
