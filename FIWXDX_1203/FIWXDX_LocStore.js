let students = JSON.parse(localStorage.getItem('students')) || [];

    function renderTable(data = students) {
      const tbody = document.querySelector('#studentTable tbody');
      tbody.innerHTML = '';
      data.forEach((student, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${student.name}</td>
          <td>${student.year}</td>
          <td>${student.major}</td>
          <td>${student.email}</td>
          <td>${student.neptun}</td>
          <td class="actions">
            <button class="modify" onclick="editStudent(${index})">Módosít</button>
            <button class="delete" onclick="deleteStudent(${index})">Töröl</button>
          </td>
        `;
        tbody.appendChild(row);
      });
    }

    function saveStudent(student) {
      students.push(student);
      localStorage.setItem('students', JSON.stringify(students));
      renderTable();
    }

    function deleteStudent(index) {
      if (confirm("Biztosan törölni szeretnéd?")) {
        students.splice(index, 1);
        localStorage.setItem('students', JSON.stringify(students));
        renderTable();
      }
    }

    function editStudent(index) {
      const s = students[index];
      document.getElementById('name').value = s.name;
      document.getElementById('year').value = s.year;
      document.getElementById('major').value = s.major;
      document.getElementById('email').value = s.email;
      document.getElementById('neptun').value = s.neptun;
      students.splice(index, 1);
      localStorage.setItem('students', JSON.stringify(students));
      renderTable();
    }

    document.getElementById('studentForm').addEventListener('submit', function(e) {
      e.preventDefault();
      const student = {
        name: document.getElementById('name').value,
        year: document.getElementById('year').value,
        major: document.getElementById('major').value,
        email: document.getElementById('email').value,
        neptun: document.getElementById('neptun').value
      };
      saveStudent(student);
      this.reset();
    });

    document.getElementById('search').addEventListener('input', function() {
      const term = this.value.toLowerCase();
      const filtered = students.filter(s =>
        s.name.toLowerCase().includes(term) ||
        s.year.toString().includes(term) ||
        s.major.toLowerCase().includes(term) ||
        s.email.toLowerCase().includes(term) ||
        s.neptun.toLowerCase().includes(term)
      );
      renderTable(filtered);
    });

    document.getElementById('sort').addEventListener('change', function() {
      const value = this.value;
      if (value === 'name') {
        students.sort((a, b) => a.name.localeCompare(b.name));
      } else if (value === 'year') {
        students.sort((a, b) => a.year - b.year);
      }
      renderTable();
    });

    document.getElementById('exportBtn').addEventListener('click', function() {
      const blob = new Blob([JSON.stringify(students, null, 2)], { type: 'application/json' });
      const url = URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'hallgatok.json';
      a.click();
    });

    document.getElementById('importFile').addEventListener('change', function() {
      const file = this.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
          try {
            const imported = JSON.parse(e.target.result);
            students = imported;
            localStorage.setItem('students', JSON.stringify(students));
            renderTable();
          } catch (err) {
            alert("Hibás JSON fájl!");
          }
        };
        reader.readAsText(file);
      }
    });

    renderTable();