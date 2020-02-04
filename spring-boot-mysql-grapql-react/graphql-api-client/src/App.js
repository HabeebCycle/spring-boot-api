import React, { useState } from "react";
import ApolloClient from "apollo-boost";
import { ApolloProvider } from "@apollo/react-hooks";

//components
import BookList from "./components/BookList";
import AddBook from "./components/AddBook";

//apollo client setup
const client = new ApolloClient({
	uri: "http://localhost:5100/graphql"
});

function App() {

	const[appError, setAppError] = useState("");

	const displayError = (err) => {
		setAppError(err);
	}

	return (
		<ApolloProvider client={client}>
			<div className="main">
				<div className="top-bar">
					<h1>Library Service</h1>
					<p>{appError}</p>
				</div>
				
				<BookList errorHandler={displayError}/>
				<AddBook errorHandler={displayError}/>
			</div>
		</ApolloProvider>
	);
}

export default App;