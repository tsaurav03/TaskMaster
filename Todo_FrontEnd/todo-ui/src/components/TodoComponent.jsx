import React, { useEffect, useState } from 'react';
import { getTodo, saveTodo, updateTodo } from '../services/TodoService';
import { useNavigate, useParams } from 'react-router-dom';

const TodoComponent = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [completed, setCompleted] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  function saveOrUpdateTodo(e) {
    e.preventDefault();

    const todo = { title, description, completed };
    if (id) {
      updateTodo(id, todo)
        .then(() => navigate('/todos'))
        .catch(console.error);
    } else {
      saveTodo(todo)
        .then(() => navigate('/todos'))
        .catch(console.error);
    }
  }

  useEffect(() => {
    if (id) {
      getTodo(id).then(response => {
        setTitle(response.data.title);
        setDescription(response.data.description);
        setCompleted(response.data.completed);
      }).catch(console.error);
    }
  }, [id]);

  return (
    <div className="container">
      <div className="card">
        <div className="card-body">
          <h2 className="text-center">{id ? 'Update Todo' : 'Add Todo'}</h2>
          <form>
            <div className="form-group mb-2">
              <label>Todo Title:</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Todo Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </div>

            <div className="form-group mb-2">
              <label>Todo Description:</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Todo Description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              />
            </div>

            <div className="form-group mb-2">
              <label>Todo Completed:</label>
              <select
                className="form-control"
                value={completed}
                onChange={(e) => setCompleted(e.target.value)}
              >
                <option value="false">No</option>
                <option value="true">Yes</option>
              </select>
            </div>

            <button className="btn btn-success" onClick={saveOrUpdateTodo}>Submit</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default TodoComponent;
