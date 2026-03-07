import { Outlet, Link } from 'react-router-dom';

export default function Layout() {
  return (
    <>
      <nav className="navbar navbar-expand-lg bg-primary navbar-dark">
        <div className="container-fluid">
          <Link className="navbar-brand ms-2" to="/">Frontend</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
        </div>
      </nav>
      <div className="container py-3">
        <h2 className="text-center">Hotel Project</h2>
        <hr />
        <div className="card shadow-sm">
          <div className="card-body">
            <Outlet />
          </div>
        </div>
      </div>
    </>
  );
}
