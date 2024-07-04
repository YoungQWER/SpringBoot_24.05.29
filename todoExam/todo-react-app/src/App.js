import './App.css';
import React, { useState } from 'react';
import Todo from "./Todo";
import AddTodo from "./AddTodo";
import { Container, List, Paper } from '@mui/material';

function App() {
    const [items, setItems] = useState([ 
        {
            id: "0",
            title: "Hello World 0",
            done: true
        },
        {
            id: "1",
            title: "Hello World 1",
            done: false
        },
        {
            id: "2",
            title: "Hello World 2",
            done: false
        }
    ]);

    const addItem = (item) => {
        item.id = "ID-" + items.length;
        item.done = false;
        setItems([...items, item]);
        console.log("item: ", items);
    };

    const deleteItem = (item) => {
        const newItems = items.filter(e => e.id !== item.id);
        setItems([...newItems]);
    };

    let todoItems =
        items.length > 0 &&
        <Paper style={{ margin: 16 }}>
            <List>
                {items.map((item) => (
                    <Todo item={item} key={item.id} deleteItem={deleteItem} />
                ))}
            </List>
        </Paper>;

    return (
        <div className="App">
            <Container maxWidth="md">
                <AddTodo addItem={addItem} />
                <div className="TodoList">{todoItems}</div>
            </Container>
        </div>
    );
}

export default App;
