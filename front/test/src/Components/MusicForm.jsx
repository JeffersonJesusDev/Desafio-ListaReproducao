import { useState, useEffect } from "react";
import "../styles/app.css";

const API_URL = "http://localhost:8080";

const MusicForm = () => {
  const [musicas, setMusicas] = useState([]);
  const [playlists, setPlaylists] = useState([]);
  const [novaMusica, setNovaMusica] = useState({
    titulo: "",
    artista: "",
    album: "",
    ano: "",
    genero: "",
  });
  const [novaPlaylist, setNovaPlaylist] = useState({
    nome: "",
    descrição: "",
    músicas: [],
  });
  const [showModal, setShowModal] = useState(false);
  const [itemToDelete, setItemToDelete] = useState(null);
  const [deleteType, setDeleteType] = useState("");

  const fetchOptions = {
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  };

  const fetchMusicas = async () => {
    try {
      const response = await fetch(`${API_URL}/lists`, fetchOptions);
      const data = await response.json();
      setMusicas(data);
    } catch (error) {
      console.error("Erro ao buscar músicas:", error);
    }
  };

  const fetchPlaylists = async () => {
    try {
      const response = await fetch(`${API_URL}/lists`, fetchOptions);
      const data = await response.json();
      setPlaylists(data);
    } catch (error) {
      console.error("Erro ao buscar playlists:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${API_URL}/lists`, {
        ...fetchOptions,
        method: "POST",
        body: JSON.stringify(novaMusica),
      });
      if (response.ok) {
        fetchMusicas();
        setNovaMusica({
          titulo: "",
          artista: "",
          album: "",
          ano: "",
          genero: "",
        });
      }
    } catch (error) {
      console.error("Erro ao adicionar música:", error);
    }
  };

  const handleSubmitPlaylist = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${API_URL}/lists`, {
        ...fetchOptions,
        method: "POST",
        body: JSON.stringify(novaPlaylist),
      });
      if (response.ok) {
        fetchPlaylists();
        setNovaPlaylist({
          nome: "",
          descrição: "",
          músicas: [],
        });
      }
    } catch (error) {
      console.error("Erro ao criar playlist:", error);
    }
  };

  const confirmDelete = (id, type) => {
    setItemToDelete(id);
    setDeleteType(type);
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    confirmDelete(id, "music");
  };

  const handleDeletePlaylist = async (id) => {
    confirmDelete(id, "playlist");
  };

  const handleConfirmDelete = async () => {
    try {
      if (deleteType === "music") {
        await fetch(`${API_URL}/lists/${itemToDelete}`, {
          ...fetchOptions,
          method: "DELETE",
        });
        fetchMusicas();
      } else {
        await fetch(`${API_URL}/lists/${itemToDelete}`, {
          ...fetchOptions,
          method: "DELETE",
        });
        fetchPlaylists();
      }
      setShowModal(false);
    } catch (error) {
      console.error("Erro ao deletar:", error);
    }
  };

  useEffect(() => {
    fetchMusicas();
    fetchPlaylists();
  }, []);

  return (
    <div className="container">
      <div className="new-playlist-section">
        <h2>Criar Nova Lista</h2>
        <form onSubmit={handleSubmitPlaylist} className="playlist-form">
          <input
            type="text"
            placeholder="Nome da Lista"
            value={novaPlaylist.nome}
            onChange={(e) =>
              setNovaPlaylist({ ...novaPlaylist, nome: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Descrição"
            value={novaPlaylist.descrição}
            onChange={(e) =>
              setNovaPlaylist({ ...novaPlaylist, descrição: e.target.value })
            }
          />
          <button type="submit">Criar Lista</button>
        </form>
      </div>

      <div className="music-container">
        <h2>Adicionar Nova Música</h2>
        <form onSubmit={handleSubmit} className="music-form">
          <input
            type="text"
            placeholder="Título"
            value={novaMusica.titulo}
            onChange={(e) =>
              setNovaMusica({ ...novaMusica, titulo: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Artista"
            value={novaMusica.artista}
            onChange={(e) =>
              setNovaMusica({ ...novaMusica, artista: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Álbum"
            value={novaMusica.album}
            onChange={(e) =>
              setNovaMusica({ ...novaMusica, album: e.target.value })
            }
          />
          <input
            type="number"
            placeholder="Ano"
            value={novaMusica.ano}
            onChange={(e) =>
              setNovaMusica({ ...novaMusica, ano: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Gênero"
            value={novaMusica.genero}
            onChange={(e) =>
              setNovaMusica({ ...novaMusica, genero: e.target.value })
            }
          />
          <button type="submit">Adicionar Música</button>
        </form>

        <h2>Lista de Músicas</h2>
        <div className="music-list">
          {musicas.map((musica, index) => (
            <div key={index} className="music-card">
              <h3>{musica.titulo}</h3>
              <p>Artista: {musica.artista}</p>
              <p>Álbum: {musica.album}</p>
              <p>Ano: {musica.ano}</p>
              <p>Gênero: {musica.genero}</p>
              <button onClick={() => handleDelete(musica.id)}>Deletar</button>
            </div>
          ))}
        </div>
      </div>

      <div className="playlists-section">
        <h2>Listas de Reprodução</h2>
        <div className="playlist-grid">
          {playlists.map((playlist) => (
            <div key={playlist.id} className="playlist-card">
              <div>
                <h3>{playlist.nome}</h3>
                <p>{playlist.descrição}</p>
                <p>Músicas: {playlist.músicas?.length || 0}</p>
              </div>
              <button
                onClick={() => handleDeletePlaylist(playlist.id)}
                className="delete-btn"
              >
                Deletar Lista
              </button>
            </div>
          ))}
        </div>
      </div>

      {showModal && (
        <div className="modal-overlay">
          <div className="modal">
            <h3>Confirmar exclusão</h3>
            <p>Tem certeza que deseja excluir este item?</p>
            <div className="modal-buttons">
              <button onClick={handleConfirmDelete}>Confirmar</button>
              <button onClick={() => setShowModal(false)}>Cancelar</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default MusicForm;