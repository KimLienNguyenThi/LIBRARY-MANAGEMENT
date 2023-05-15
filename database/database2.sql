DROP DATABASE QuanLyThuVien;
CREATE DATABASE QuanLyThuVien;
USE QuanLyThuVien;

CREATE TABLE NhanVien (
	MaNV INT PRIMARY KEY AUTO_INCREMENT,
	HoTenNV VARCHAR(50),
	DiaChi VARCHAR(100),
	SDT VARCHAR(50),
	GioiTinh VARCHAR(50),
	NgaySinh DATE -- CHECK (NgaySinh <= sysdate())
);



CREATE TABLE LoSach(
	MaLo INT PRIMARY KEY AUTO_INCREMENT,
    TenNhaCC VARCHAR(100) NOT NULL,
    NgayNhap DATE NOT NULL,
    SDTNCC VARCHAR(50),
    DiaChiCC VARCHAR(100),
    TongTienNhap INT 
);
CREATE TABLE ChiTietLo (
	MaDS INT PRIMARY KEY AUTO_INCREMENT,
 --    TenSach VARCHAR(100),
--     TheLoai VARCHAR(50),
--     NXB VARCHAR(100) NOT NULL ,
   --  TacGia VARCHAR(50) NOT NULL ,
    SoLuong INT,
    MaLo INT,
    --  MaSach INT ,
--      FOREIGN KEY (MaSach) REFERENCES Sach(Masach),
    FOREIGN KEY (MaLo) REFERENCES LoSach(MaLo)
);
CREATE TABLE SACH(
	MaSach INT PRIMARY KEY AUTO_INCREMENT,
    TenSach VARCHAR(100) NOT NULL ,
    TheLoai VARCHAR(100) NOT NULL,
    NamXB YEAR NOT NULL, 
    NXB VARCHAR(100) NOT NULL,
    TacGia VARCHAR(50) NOT NULL ,
    NgonNgu varchar(100),
   
    TinhTrang varchar(100),
    GiaSach INT NOT NULL,
     MaDS INT ,
    FOREIGN KEY (MaDS) REFERENCES ChiTietLo(MaDS)
);
CREATE TABLE DocGia(
	MaDG INT PRIMARY KEY AUTO_INCREMENT,
    TenDG VARCHAR(50),
    SDT VARCHAR(50),
    DiaChi VARCHAR(100),
    NgaySinh DATE -- CHECK (NgaySinh <= sysdate())
);

CREATE TABLE TheDocGia (
	MaThe INT PRIMARY KEY AUTO_INCREMENT,
    NgayDK DATE,
    HanThe DATE,
    MaDG INT,
    PhiDK INT,
    FOREIGN KEY (MaDG) REFERENCES DocGia(MaDG)
);

CREATE TABLE PhieuMuon(
	MaPM INT PRIMARY KEY AUTO_INCREMENT,
    MaThe INT ,
    NgayMuon DATE,
    NgayTra DATE,
    TinhTrang varchar (100) default "Chưa trả", 
    FOREIGN KEY (MaThe) REFERENCES thedocgia (MaThe)
);

CREATE TABLE ChiTietPhieuMuon (
	MaPM INT ,
    MaSach INT ,
    PRIMARY KEY (MaPM, MaSach),
    FOREIGN KEY (MaPM) REFERENCES PhieuMuon(MaPM),
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)
);



CREATE TABLE Login (
MANV INT,
FOREIGN KEY (MANV) REFERENCES NhanVien(MANV),
MatKhau VARCHAR(20) NOT NULL
);

insert into NhanVien (MaNV, HoTenNV, DiaChi, SDT, GioiTinh, NgaySinh) values (1, 'Niki Louiset', '61737 Brown Trail', '393-744-1011', 'Female', '2003-12-25');

insert into Login (MaNV, MatKhau) VALUE(1, 123);

insert into LoSach (MaLo, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (1, 'Công Ty TNHH Đăng Nguyên', '2023-11-10', '498-309-7948', '187 Giảng Võ, Q. Đống Đa, Hà Nội', 50);
insert into LoSach (MaLo, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (2, 'Công Ty Cổ Phần Sách Mcbooks', '2023-12-10', '109-9238-316', '14/35, Đào Duy Anh, P.9, Q. Phú Nhuận, Tp. Hồ Chí Minh (TPHCM)', 42);
insert into LoSach (MaLo, TenNhacc, NgayNhap, SdtNCC, DiaChICC, TongTienNhap) values (3, 'Nhà Sách Trực Tuyến BOOKBUY.VN', '2022-6-23', '494-233-5837', '147 Pasteur, P. 6, Q. 3, Tp. Hồ Chí Minh (TPHCM)', 78);
insert into LoSach (MaLo, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (4, 'Công Ty Cổ Phần Sách & Thiết Bị Trường Học Kiên Giang', '2022-7-1', '430-195-4725', '289A Khuất Duy Tiến, P. Trung Hòa, Q. Cầu Giấy, Hà Nội', 78);
insert into LoSach (MaLo, TenNhacc, NgayNhap, SdtNCC, DiaChiCC, TongTienNhap) values (5, 'TNHH Văn Hóa Việt Long', '2023-10-1', '2023-10-12', 'Lô 34E, Khu Đấu Giá 3ha, P. Phúc Diễm, Q. Bắc Từ Liêm, Hà Nội', 72);

insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (1, 'Atalanta Huddle', '175-752-1117', '2957 Ridge Oak Court', '2003-12-9');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (2, 'Tedie Ellick', '958-883-4049', '51 Cody Road', '2001-03-20');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (3, 'Garry Baford', '330-640-2234', '7 Main Way', '1999-02-10');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (4, 'Zebulen Cutford', '864-511-3252', '498 Ludington Park', '2004-11-9');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (5, 'Otis Bazoche', '765-570-6904', '74 Myrtle Point', '1998-01-12');
insert into DocGia (MaDG, TenDG, SDT, DiaChi, NgaySinh) values (6, 'Trstram Leven', '665-546-0991', '158 Waubesa Plaza', '1976-02-03');

insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (1, '2023-2-5', '2022-8-1',3,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (2, '2023-6-3', '2022-6-21',4,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (3, '2023-12-3', '2022-4-22',1,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (4, '2023-3-1', '2022-8-14',2,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (5, '2023-3-4', '2022-5-20',5,100000);
insert into TheDocGia (MaThe, NgayDK, HanThe,MaDG, PhiDK) values (6, '2023-3-4', '2022-5-20',6,100000);

-- *************** Sửa *****************
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra) values (1, 4, '2023-1-31', '2023-2-5');
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra) values (2, 1, '2023-1-31', '2023-4-28');
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra) values (3, 3, '2023-1-31', '2023-3-7');
insert into PhieuMuon (MaPM, MaThe, NgayMuon, NgayTra) values (4, 5, '2023-5-26', '2023-5-30');


-- **************************************

insert into ChiTietLo (MaDS,  MaLo,  SoLuong) values (1, 1,  50);
insert into ChiTietLo (MaDS, MaLo,  SoLuong) values (2, 2, 50);
insert into ChiTietLo (MaDS, MaLo,SoLuong) values (3, 5,  50);
insert into ChiTietLo (MaDS, MaLo, SoLuong) values (4, 2,  50);
insert into ChiTietLo (MaDS, MaLo,  SoLuong) values (5,  4,  50);
-- ********************************

insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia, NgonNgu, TinhTrang, GiaSach, MaDS) values (1, 'Cơ hội của Chúa', 'Tiểu thuyết', 1999, 'Kim Đồng', 'Nguyễn Việt Hà',  'Tiếng Việt', 'Còn', 70000,1);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (2, 'Tuổi 20 yêu dấu', 'Truyện', 2018, 'Trẻ', 'Nguyễn Huy Thiệp',  'Tiếng Việt', 'Còn', 28000,2);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (3, 'Tuổi thơ dữ dội ', 'Thơ', 1988, 'Kim Đồng', 'Phùng Quán',  'Tiếng Pháp', 'Còn', 18000,4);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (4, 'Đắc nhân tâm', 'Kinh doanh', 1936, 'NXB Trẻ', 'Dale Carnegie',  'Tiếng Việt', 'Còn', 120000,1);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (5, 'Sức mạnh của những suy nghĩ tích cực', 'Tâm lý học', 2006, 'NXB Thế giới', 'Norman Vincent Peale',  'Tiếng Việt', 'Hết', 89000,5);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (6, 'Dế Mèn phiêu lưu ký', 'Truyện thiếu nhi', 1941, 'Kim Đồng', 'Tô Hoài', 'Tiếng Việt', 'Hết', 56000,3);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (7, 'Sherlock Holmes: Cuộc phiêu lưu của huyền thoại Baskerville', 'Trinh thám', 1902, 'NXB Văn học', 'Arthur Conan Doyle',  'Tiếng Việt', 'Hết', 99000,4);
insert into Sach (MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,  NgonNgu, TinhTrang, GiaSach, MaDS) values (8, 'Bí mật của tư duy giàu có', 'Kinh doanh', 2005, 'NXB Phụ Nữ', 'T. Harv Eker',  'Tiếng Việt', 'Hết', 75000,2);
-- **************************************

insert into ChiTietPhieuMuon (MaPM, MaSach) values (1, 7);
insert into ChiTietPhieuMuon (MaPM, MaSach) values (2, 5);
insert into ChiTietPhieuMuon (MaPM, MaSach) values (3, 8);
insert into ChiTietPhieuMuon (MaPM, MaSach) values (4, 6);