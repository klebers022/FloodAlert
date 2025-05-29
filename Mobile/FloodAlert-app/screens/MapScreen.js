import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function HomeScreen() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Flood Alert</Text>
      <Text style={styles.subtitle}>Status Atual da Cidade</Text>
      <Text>Sem alertas críticos no momento.</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#fff' },
  title: { fontSize: 26, fontWeight: 'bold', color: '#005A9C' },
  subtitle: { fontSize: 18, marginBottom: 10 },
});





// import React from 'react';
// import { View, Text, StyleSheet, Dimensions } from 'react-native';
// import MapView, { Marker, Polygon } from 'react-native-maps';
// import { Ionicons } from '@expo/vector-icons';

// export default function MapScreen() {
//   return (
//     <View style={styles.container}>
//       <Text style={styles.title}>Mapa Interativo</Text>

//       <MapView
//         style={styles.map}
//         initialRegion={{
//           latitude: -23.55052,
//           longitude: -46.633308,
//           latitudeDelta: 0.05,
//           longitudeDelta: 0.05,
//         }}
//       >
//         {/* Exemplo de Área de Risco */}
//         <Polygon
//           coordinates={[
//             { latitude: -23.550, longitude: -46.635 },
//             { latitude: -23.552, longitude: -46.637 },
//             { latitude: -23.553, longitude: -46.632 },
//             { latitude: -23.551, longitude: -46.630 },
//           ]}
//           strokeColor="#FF0000"
//           fillColor="rgba(255, 0, 0, 0.3)"
//           strokeWidth={2}
//         />

//         {/* Marker de Abrigo */}
//         <Marker
//           coordinate={{ latitude: -23.552, longitude: -46.633 }}
//           title="Abrigo Central"
//           description="Capacidade: 80/100"
//         >
//           <Ionicons name="home" size={30} color="#005A9C" />
//         </Marker>

//         {/* Marker de Alerta */}
//         <Marker
//           coordinate={{ latitude: -23.550, longitude: -46.638 }}
//           title="Alerta de Enchente"
//           description="Perigo Alto - Região Sul"
//         >
//           <Ionicons name="alert" size={30} color="#FFA500" />
//         </Marker>
//       </MapView>
//     </View>
//   );
// }
// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     backgroundColor: '#FFFFFF',
//     paddingTop: 50,
//     paddingHorizontal: 20,
//   },
//   title: {
//     fontSize: 26,
//     fontWeight: 'bold',
//     color: '#005A9C',
//     marginBottom: 10,
//   },
//   map: {
//     width: Dimensions.get('window').width - 40,
//     height: Dimensions.get('window').height * 0.75,
//     borderRadius: 12,
//   },
// });
