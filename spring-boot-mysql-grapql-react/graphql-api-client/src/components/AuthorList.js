import React from 'react'
import { getAuthorsQuery } from "../schema/queries";
import { useQuery } from "@apollo/react-hooks";


const AuthorList = (props) => {
    const { loading, error, data } = useQuery(getAuthorsQuery);

    const displayAuthors = () => {
		if (loading) {
			return <div>Loading Authors...</div>;
		} else {
			if (error) {
				props.errorHandler(error.message);
				return <div>{error.message}</div>;
			}
			return data.authors.map(author => {
				return (
					<li
						key={author.id}
						onClick={e => {
							props.detailsHandler("Author", author.id);
						}}
					>
						{author.name}
					</li>
				);
			});
		}
	};
    
    return (
        <div>
			<ul id="author-list">{displayAuthors()}</ul>
		</div>
    )
}

export default AuthorList
