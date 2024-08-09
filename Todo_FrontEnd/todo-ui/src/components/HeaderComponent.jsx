import React from 'react'

const HeaderComponent = () => {
  return (
    <div>
        <header>
            <nav className='navbar navbar-expand-md navbar-dark bg-dark justify-content-center'>
                <div>
                    <a href='http://localhost:3000' className='navbar-brand'>
                        TaskMaster
                    </a>
                </div>
            </nav>
        </header>

    </div>
  )
}

export default HeaderComponent