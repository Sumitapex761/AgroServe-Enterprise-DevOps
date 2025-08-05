// src/pages/DashboardPage.jsx
import React from "react";
import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  ResponsiveContainer,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Legend
} from "recharts";
import { Container, Row, Col, Card } from "react-bootstrap";
import "../styles/DashboardPage.css";

// Chart Data
const pieData = [
  { name: "Irrigation Setup", value: 40 },
  { name: "Tractor Rental", value: 30 },
  { name: "Pesticide Spray", value: 30 }
];
const COLORS = ["#198754", "#00C49F", "#FFBB28"];

const barData = [
  { month: "Jan", bookings: 30 },
  { month: "Feb", bookings: 50 },
  { month: "Mar", bookings: 40 },
  { month: "Apr", bookings: 70 }
];

// Dashboard Stats (dummy values)
const stats = [
  { title: "Total Bookings", value: 190, icon: "📑" },
  { title: "Active Services", value: 12, icon: "🛠" },
  { title: "Revenue (₹)", value: "75,000", icon: "💰" },
  { title: "Pending Requests", value: 8, icon: "⏳" }
];

export default function DashboardPage() {
  return (
    <Container className="py-5 dashboard-container">
      <h2 className="text-center mb-4 text-success">Dashboard Overview</h2>

      {/* Stats Cards */}
      <Row className="mb-4">
        {stats.map((item, index) => (
          <Col md={3} sm={6} key={index}>
            <Card className="shadow stat-card text-center">
              <Card.Body>
                <div className="stat-icon">{item.icon}</div>
                <h4 className="stat-value">{item.value}</h4>
                <p className="stat-title">{item.title}</p>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {/* Charts Section */}
      <Row className="g-4">
        {/* Pie Chart Card */}
        <Col md={6}>
          <Card className="shadow chart-card">
            <Card.Body>
              <h5 className="text-center mb-3">Service Booking Distribution</h5>
              <ResponsiveContainer width="100%" height={300}>
                <PieChart>
                  <Pie
                    data={pieData}
                    cx="50%"
                    cy="50%"
                    outerRadius={100}
                    fill="#198754"
                    dataKey="value"
                    label
                  >
                    {pieData.map((entry, index) => (
                      <Cell
                        key={`cell-${index}`}
                        fill={COLORS[index % COLORS.length]}
                      />
                    ))}
                  </Pie>
                  <Tooltip />
                </PieChart>
              </ResponsiveContainer>
            </Card.Body>
          </Card>
        </Col>

        {/* Bar Chart Card */}
        <Col md={6}>
          <Card className="shadow chart-card">
            <Card.Body>
              <h5 className="text-center mb-3">Monthly Bookings</h5>
              <ResponsiveContainer width="100%" height={300}>
                <BarChart data={barData}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="month" />
                  <YAxis />
                  <Tooltip />
                  <Legend />
                  <Bar dataKey="bookings" fill="#198754" radius={[10, 10, 0, 0]} />
                </BarChart>
              </ResponsiveContainer>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
