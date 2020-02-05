import React from "react";
import { getBookQuery, getAuthorQuery } from "../schema/queries";
import { useQuery } from "@apollo/react-hooks";

const ViewDetails = ({ view }) => {
	const viewQuery = view.type === "Book" ? getBookQuery : getAuthorQuery;
    const viewId = view.type === "Book" ? {"bookId": view.id} : {"authorId": view.id};
    const { loading, error, data } = useQuery(viewQuery, {		
		variables: viewId
	});

	const displayDetails = () => {
		if (loading) {
			return <div>Loading {view.type} Details...</div>;
		} else {
			if (error) {
				return <div>{error.message}</div>;
			}
			if(view.type === "Book"){
				const { book } = data;
				if (book) {
					return (
						<div>
							<h2>{book.name}</h2>
							<p>Genre &nbsp; {book.genre}</p>
							<p>
								Author &nbsp; {book.author.name} - {book.author.age} years
							</p>
							<p>- All books by this author:</p>
							<ul className="other-books">
								{book.author.books.map(item => {
									return (
										<li key={item.id}>
											{item.name} - {item.genre}
										</li>
									);
								})}
							</ul>
						</div>
					);
				} else {
					return <div>No book selected...</div>;
				}
			}else{
				const { author } = data;
				if (author) {
					return (
						<div>
							<h2>{author.name}</h2>
							<p>Age &nbsp; {author.age} years</p>
							<p>- All books by this author:</p>
							<ul className="other-books">
								{author.books.map(item => {
									return (
										<li key={item.id}>
											{item.name} - {item.genre}
										</li>
									);
								})}
							</ul>
						</div>
					);
				} else {
					return <div>No author selected...</div>;
				}
			}
		}
	};

	return (
		<div id="book-details">
			{displayDetails()}
		</div>
	);
};

export default ViewDetails;
