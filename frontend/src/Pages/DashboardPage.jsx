import React from "react";
import { PieChart, Pie, Cell, Tooltip, ResponsiveContainer, BarChart, Bar, XAxis, YAxis, CartesianGrid, Legend } from "recharts";
import { Container, Row, Col } from "react-bootstrap";

const pieData = [
  { name: "Irrigation Setup", value: 40 },
  { name: "Tractor Rental", value: 30 },
  { name: "Pesticide Spray", value: 30 }
];

const COLORS = ["#0088FE", "#00C49F", "#FFBB28"];

const barData = [
  { month: "Jan", bookings: 30 },
  { month: "Feb", bookings: 50 },
  { month: "Mar", bookings: 40 },
  { month: "Apr", bookings: 70 }
];

export default function DashboardPage() {
  return (
    <Container className="my-4">
      <h2 className="text-center mb-4">Dashboard</h2>
      <Row>
        <Col md={6}>
          <h5 className="text-center">Service Booking Distribution</h5>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie
                data={pieData}
                cx="50%"
                cy="50%"
                outerRadius={100}
                fill="#8884d8"
                dataKey="value"
                label
              >
                {pieData.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
        </Col>
        <Col md={6}>
          <h5 className="text-center">Monthly Bookings</h5>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={barData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="month" />
              <YAxis />
              <Tooltip />
              <Legend />
              <Bar dataKey="bookings" fill="#82ca9d" />
            </BarChart>
          </ResponsiveContainer>
        </Col>
      </Row>
    </Container>
  );
}
