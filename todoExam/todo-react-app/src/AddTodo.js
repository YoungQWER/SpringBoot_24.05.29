import { Button, Grid, TextField } from '@mui/material';
import React, { useState } from 'react';

const AddTodo = (props) => {

	const [item,setItem] = useState({title: ""});
	const addItem = props.addItem;


	//onButtonClick 함수 작성
	const onButtonClick = () => {
		addItem(item);
		setItem({title : ""});
	}

	const enterKeyEventhandler = (e)=>{
		console.log(e.key);
		if(e.key === 'Enter'){
			onButtonClick();
		}
	}

	//onInputChange 함수 작성
	const onInputChange = (e)=> {
		setItem({title: e.target.value});		
	}

	return (
		<div>
			<Grid container style={{marginTop: 20}}>
				<Grid xs={11} md={11} item style={{paddingRight: 16}}>
					<TextField placeholder='Add Todo here? ' fullWidth onChange={onInputChange} 
					onKeyDown={enterKeyEventhandler}
					value={item.title}/>
				</Grid>
				<Grid xs={1} md={1} item>
					<Button fullWidth style={{height: '100%'}} color='secondary' variant='outlined' 
					onClick={onButtonClick}				
					>+</Button>
				</Grid>
			</Grid>
		</div>
	);
};

export default AddTodo;