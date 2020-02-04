import { gql } from "apollo-boost";

export const getAuthorsQuery = gql`
	{
		authors {
			id
			name
			age
		}
	}
`;

export const getBooksQuery = gql`
	{
		books {
			id
			name
		}
	}
`;

export const getBookQuery = gql`
    query($bookId: ID){
        book(id: $bookId) {
			id
			name
			genre
			author {
				id
				name
				age
				books {
					id
					name
					genre
				}
			}
		}
	}
`;

export const getAuthorQuery = gql`
	query($authorId: ID) {
		author(id: $authorId) {
			id
			name
			age
			books {
				id
				name
				genre
			}
		}
	}
`;

export const addBookMutation = gql`
	mutation addBook($name: String!, $genre: String, $authorId: ID!) {
		addBook(name: $name, genre: $genre, author: $authorId) {
			id
			name
			genre
			author {
				id
				name
				age
				books {
					id
					name
					genre
				}
			}
		}
	}
`;

export const addAuthorMutation = gql`
	mutation addAuthor($name: String!, $age: Int) {
		addAuthor(name: $name, age: $age) {
			id
			name
			age
			books {
				id
				name
				genre
			}
		}
	}
`;

export const updateBookMutation = gql`
	mutation updateBook(
		$bookId: ID!
		$name: String!
		$genre: String
		$authorId: ID!
	) {
		updateBook(id: $bookId, name: $name, genre: $genre, author: $authorId) {
			id
			name
			genre
			author {
				id
				name
				age
				books {
					id
					name
					genre
				}
			}
		}
	}
`;

export const updateAuthorMutation = gql`
	mutation updateAuthor($authorId: ID!, $name: String!, $age: Int) {
		addAuthor(id: $authorId, name: $name, age: $age) {
			id
			name
			age
			books {
				id
				name
				genre
			}
		}
	}
`;

export const deleteBookMutation = gql`
	mutation deleteBook($bookId: ID!) {
		deleteBook(id: $bookId) {
			id
		}
	}
`;

export const deleteAuthorMutation = gql`
	mutation deleteAuthor($authorId: ID!) {
		deleteAuthor(id: $authorId) {
			id
		}
	}
`;
