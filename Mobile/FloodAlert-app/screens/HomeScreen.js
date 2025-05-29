import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import { Ionicons, MaterialIcons, FontAwesome5, Entypo } from '@expo/vector-icons';

export default function HomeScreen() {
  return (
    <ScrollView style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <Ionicons name="water" size={32} color="#005A9C" />
        <Text style={styles.title}>Flood Alert</Text>
      </View>

      {/* Alerta Ativo */}
      <View style={styles.alertBox}>
        <MaterialIcons name="error-outline" size={32} color="#FFA500" />
        <View style={{ marginLeft: 10 }}>
          <Text style={styles.alertTitle}>🚨 Alerta de enchente</Text>
          <Text style={styles.alertDesc}>Região Central</Text>
        </View>
      </View>

      {/* Menu Grid */}
      <View style={styles.menuGrid}>
        <TouchableOpacity style={styles.menuButton}>
          <Entypo name="map" size={24} color="#FFFFFF" />
          <Text style={styles.menuText}>Mapa Interativo</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.menuButton}>
          <Ionicons name="business" size={24} color="#FFFFFF" />
          <Text style={styles.menuText}>Abrigos</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.menuButton}>
          <Ionicons name="navigate" size={24} color="#FFFFFF" />
          <Text style={styles.menuText}>Rotas Seguras</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.menuButtonAlert}>
          <Ionicons name="create" size={24} color="#FFFFFF" />
          <Text style={styles.menuText}>Registrar Ocorrência</Text>
        </TouchableOpacity>
      </View>

      {/* Status Atual */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>Status Atual</Text>
        <View style={styles.statusRow}>
          <Text style={styles.statusLow}>● Baixo risco</Text>
          <Text style={styles.statusText}>Região Norte</Text>
        </View>
        <View style={styles.statusRow}>
          <Text style={styles.statusMedium}>● Médio risco</Text>
          <Text style={styles.statusText}>Região Sul</Text>
        </View>
        <View style={styles.statusRow}>
          <Text style={styles.statusHigh}>● Alto risco</Text>
          <Text style={styles.statusText}>Região Leste</Text>
        </View>
      </View>

      {/* Últimos Alertas */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>Últimos Alertas</Text>
        <View style={styles.statusRow}>
          <Text style={styles.statusMedium}>● Médio risco</Text>
          <Text style={styles.statusText}>Há 5 horas</Text>
        </View>
        <View style={styles.statusRow}>
          <Text style={styles.statusLow}>● Baixo risco</Text>
          <Text style={styles.statusText}>Há 1 dia</Text>
        </View>
      </View>
    </ScrollView>
  );
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 20,
    paddingTop: 50,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 20,
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#005A9C',
    marginLeft: 8,
  },
  alertBox: {
    backgroundColor: '#FFF4E5',
    padding: 15,
    borderRadius: 12,
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 20,
    borderLeftWidth: 6,
    borderLeftColor: '#FFA500',
  },
  alertTitle: {
    color: '#FFA500',
    fontSize: 18,
    fontWeight: 'bold',
  },
  alertDesc: {
    color: '#333333',
    fontSize: 14,
    marginTop: 4,
  },
  menuGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
    marginBottom: 20,
  },
  menuButton: {
    backgroundColor: '#005A9C',
    width: '48%',
    height: 90,
    borderRadius: 12,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 10,
  },
  menuButtonAlert: {
    backgroundColor: '#FFA500',
    width: '48%',
    height: 90,
    borderRadius: 12,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 10,
  },
  menuText: {
    color: '#FFFFFF',
    marginTop: 8,
    fontSize: 14,
    fontWeight: '600',
    textAlign: 'center',
  },
  card: {
    backgroundColor: '#F5F5F5',
    borderRadius: 12,
    padding: 15,
    marginBottom: 20,
  },
  cardTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#333333',
  },
  statusRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 6,
  },
  statusLow: {
    color: '#005A9C',
    fontWeight: 'bold',
  },
  statusMedium: {
    color: '#FFA500',
    fontWeight: 'bold',
  },
  statusHigh: {
    color: '#FF0000',
    fontWeight: 'bold',
  },
  statusText: {
    color: '#333333',
  },
});
