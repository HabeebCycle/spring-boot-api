import React, { useState } from "react";
import ApolloClient from "apollo-boost";
import { ApolloProvider } from "@apollo/react-hooks";

//components
import BookList from "./components/BookList";
import AddBook from "./components/AddBook";
import AddAuthor from "./components/AddAuthor";
import AuthorList from "./components/AuthorList";
import ViewDetails from "./components/ViewDetails";

//apollo client setup
const client = new ApolloClient({
	uri: "http://localhost:5100/graphql"
});

function App() {

	const[appError, setAppError] = useState("");	
    const [selected, setSelected] = useState({});

	const displayError = err => {
		setAppError(err);
	}

	const displayDetails = (type, id) => {
		setSelected({type, id});
	}

	return (
		<ApolloProvider client={client}>
			<div className="main">
				<div className="top-bar">
					<h1>Library Service</h1>
					<p>{appError}</p>
				</div>
				
				<BookList errorHandler={displayError} detailsHandler={displayDetails}/>
				<AuthorList errorHandler={displayError} detailsHandler={displayDetails}/>
				<AddBook errorHandler={displayError}/>
				<AddAuthor errorHandler={displayError}/>
				<ViewDetails view={selected}/>
			</div>
		</ApolloProvider>
	);
}

export default App;